import { useContext, useEffect, useState } from "react";
import { UserContext } from "../../model/utils/context/UserContext";
import { useNavigate, useParams } from "react-router-dom";

import loadData from "./../../model/utils/hooks.jsx";

export default function ModifAbs() {
  const {id} = useParams();
  const [dt_debut, setDtDebut] = useState(null);
  const dateAujourdhui = new Date();
  const navigate = useNavigate();
  const { updateData } = useContext(UserContext);

  const [isFormValid, setIsFormValid] = useState(false);

  const { loadedData } = loadData(
    "http://localhost:8082/api/absence/" + id
      );

  
  useEffect(() => {}, [loadedData]);

  useEffect(() => {
    const form = document.querySelector(".abs-form");
    const dtDebutInput = form.querySelector('[name="dt_debut"]');
    const dtFinInput = form.querySelector('[name="dt_fin"]');
    const handleFormChange = () => {
      const isDtDebutValid = dtDebutInput.validity.valid;
      const isDtFinValid =
        dtFinInput.validity.valid && dtFinInput.value >= dtDebutInput.value;
      setIsFormValid(isDtDebutValid && isDtFinValid);
    };

    dtDebutInput.addEventListener("change", handleFormChange);
    dtFinInput.addEventListener("change", handleFormChange);

    return () => {
      dtDebutInput.removeEventListener("change", handleFormChange);
      dtFinInput.removeEventListener("change", handleFormChange);
    };
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = {};
    formData.forEach((value, key) => {
      data[key] = value;
    });
    updateData("http://localhost:8082/api/absence/update/" + id, data);

    setTimeout(() => {
      navigate("/absence");
    }, 100);
  };
  return (
    <>
      <h1>Modifier une demande d&apos;absence</h1>
      <div className="abs">
        <table className="abs-recap">
          <thead>
            <tr>
              <th>Numéro de demande</th>
              <th>Motif</th>
              <th>Date de début</th>
              <th>Date de fin</th>
              <th>Type</th>
            </tr>
          </thead>
          <tbody>
            {loadedData && (
              <tr>
                <td>{loadedData.id}</td>
                <td>{loadedData.motif}</td>
                <td>{loadedData.dt_debut}</td>
                <td>{loadedData.dt_fin}</td>
                <td>{loadedData.type}</td>
              </tr>
            )}
          </tbody>
        </table>
        <h2>Informations à modifier</h2>
        <form
          className="abs-form"
          action={`/absence/update/`}
          method="POST"
          onSubmit={handleSubmit}
        >
          <label>
            <span>
              Demande N°{loadedData.id} de {loadedData.fullName}
            </span>
          </label>
          <label>
            <span>Type d&apos;absence</span>
            <select name="type">
              <option value="PAID_LEAVE">Congé</option>
              <option value="UNPAID_LEAVE">Congé sans solde</option>
              <option value="RTT_EMPLOYEE">RTT</option>
            </select>
          </label>
          <label>
            <span>Date de début</span>
            <input
              type="date"
              name="dt_debut"
              min={dateAujourdhui.toISOString().split("T")[0]}
              required
              onChange={(e) => setDtDebut(e.target.value)}
            />
          </label>
          <label>
            <span>Date de fin</span>
            <input type="date" name="dt_fin" required min={dt_debut} />
          </label>
          <label>
            <span>Motif</span>
            <input type="text" name="motif" />
          </label>
          <button type="submit" disabled={!isFormValid}>
            Modifier
          </button>
        </form>
      </div>
    </>
  );
}
