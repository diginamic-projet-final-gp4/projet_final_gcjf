import { useContext, useState } from "react";
import { UserContext } from "../../model/utils/context/UserContext";

import "./Absense.css";
import { useNavigate } from "react-router-dom";

export default function CreerAbsenseGroup() {
  const navigate = useNavigate();
  const [startDate, setStartDate] = useState(new Date());
  const { postData } = useContext(UserContext);

  const handleChangeDate = (e) => {
    setStartDate(e.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Affiche un le json des données du formulaire
    const formData = new FormData(event.target);
    const data = {};
    formData.forEach((value, key) => {
      data[key] = value;
    });

    postData("http://localhost:8082/api/admin/specific-absence/create", data);

    setTimeout(() => {
      navigate("/planing-ferie");
    }, 150);
  };

  return (
    <div className="abs">
      <h1>Créer une absence de groupe</h1>

      <form className="abs-form" onSubmit={handleSubmit}>
        <label>
          <span>Type d&apos;absence</span>
          <select name="type" readOnly>
            <option value="RTT_EMPLOYER">RTT Employeur</option>
            <option value="FERIEE">Feriée</option>
          </select>
        </label>
        <label>
          <span>Date de début</span>
          <input type="date" name="dtDebut" min={new Date().toISOString().split("T")[0]} onChange={handleChangeDate}/>
        </label>
        <label>
          <span>Date de fin</span>
          <input type="date" name="dt_fin" min={startDate} />
        </label>
        <label>
          <span>Motif</span>
          <input type="text" name="motif" />
        </label>
        <button type="submit">Créer</button>
      </form>
    </div>
  );
}
