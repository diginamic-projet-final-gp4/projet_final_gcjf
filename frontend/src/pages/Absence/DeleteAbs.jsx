import { useContext } from "react";
import { Link, useNavigate, useParams,  } from "react-router-dom";
import loadData from "./../../model/utils/hooks.jsx";
import { UserContext } from "../../model/utils/context/UserContext.jsx";

export default function DeleteAbs() {
  const {id} = useParams();

  const navigate = useNavigate();

  const { deleteData } = useContext(UserContext);

  const { loadedData } = loadData("http://localhost:8082/api/absence/" + id);

  function handleDeleteData() {
    deleteData("http://localhost:8082/api/absence/delete/" + id);
    setTimeout(() => {
      navigate("/absence");
    }, 150);
  }

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
                <td>{loadedData.dtDebut}</td>
                <td>{loadedData.dt_fin}</td>
                <td>{loadedData.type}</td>
              </tr>
            )}
          </tbody>
        </table>

        <button onClick={handleDeleteData}><Link to="/absence">Confirmer</Link></button>
      </div>
    </>
  );
}
