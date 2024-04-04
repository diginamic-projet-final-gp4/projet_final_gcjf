import { Link } from "react-router-dom";
import { format } from "date-fns";

import loadData from "./../../model/utils/hooks.jsx";

export default function AdminPage() {
  const { loadedData } = loadData(
    "http://localhost:8082/api/specific-absence/all"
  );

  return (
    <>
      <h1>Administration</h1>
      <div>
        <div className="admin-links">
          <Link to={`/absence/group/create`}>Créer une absence de groupe</Link>
        </div>

        <div className="classForTable">
          <h2>Absences de groupe</h2>
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Date de début</th>
                <th>Date de fin</th>
                <th>Type</th>
                <th>Motif</th>
              </tr>
            </thead>
            <tbody>
              {loadedData &&
                loadedData.map((absence) => (
                  <tr key={absence.id}>
                    <td>{absence.id}</td>
                    <td>{format(new Date(absence.dt_debut), "dd/MM/yyyy")}</td>
                    <td>{format(new Date(absence.dt_fin), "dd/MM/yyyy")}</td>
                    <td>{absence.type}</td>
                    <td>{absence.motif}</td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}
