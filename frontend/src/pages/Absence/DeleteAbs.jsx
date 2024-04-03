import { useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import loadData from "./../../model/utils/hooks.jsx";
import { UserContext } from "../../model/utils/context/UserContext.jsx";

export default function DeleteAbs() {
  let id;

  const navigate = useNavigate();

  const { deleteData } = useContext(UserContext);

  if (window.location && window.location.pathname) {
    id = window.location.pathname.split("/").pop();
  }

  const { loadedData } = loadData("http://localhost:8082/api/absence/" + id);

  function handleDeleteData() {
    deleteData("http://localhost:8082/api/absence/delete/" + id);
    navigate("/absence");
  }

  useEffect(() => {
    if (loadedData) {
      console.log(loadedData);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      <h1>Confirmer la suppression</h1>
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

        <button onClick={handleDeleteData}>Confirmer</button>
      </div>
    </>
  );
}
