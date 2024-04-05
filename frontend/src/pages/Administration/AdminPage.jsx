import { Link } from "react-router-dom";
import { format } from "date-fns";

import loadData from "./../../model/utils/hooks.jsx";

import "./Administration.css";

export default function AdminPage() {
  const { loadedData } = loadData(
    "http://localhost:8082/api/specific-absence/all"
  );

  return (
    <>
      <div className="AdminPage">
        <h1>Administration</h1>
        <div>
          <div className="admin-links">
            <Link to={`/absence/group/create`}>
              Créer une absence de groupe
            </Link>
          </div>

          <div className="Absencesdegroupe">
            <h2>Absences de groupe</h2>
            <table>
              <thead>
                <tr>
                  <th className="classNum">ID</th>
                  <th className="classDate">Date de début</th>
                  <th className="classDate">Date de fin</th>
                  <th className="classType">Type</th>
                  <th>Motif</th>
                  <th className="classAction">Actions</th>
                </tr>
              </thead>
              <tbody>
                {loadedData &&
                  loadedData.map((absence) => (
                    <tr key={absence.id}>
                      <td>{absence.id}</td>
                      <td>
                        {format(new Date(absence.dt_debut), "dd/MM/yyyy")}
                      </td>
                      <td>{format(new Date(absence.dt_fin), "dd/MM/yyyy")}</td>
                      <td>{absence.type}</td>
                      <td>{absence.motif}</td>
                      {(absence.type === "RTT_EMPLOYER" && (
                        <td className="classActionButtons">
                          {/* TODO : Implémenter la fonctionnalité de modification et de suppression dans le backend */}
                          <button className="modifier">
                            <Link to={``}>Modifier</Link>
                          </button>
                          <button className="supprimer">
                            <Link to={``}>Supprimer</Link>
                          </button>
                        </td>
                      )) || <td></td>}
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </>
  );
}
