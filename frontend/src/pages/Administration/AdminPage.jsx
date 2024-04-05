import { Link } from "react-router-dom";
import { format } from "date-fns";
import "./Administration.css"

import loadData from "./../../model/utils/hooks.jsx";
import { useContext } from "react";
import { UserContext } from "../../model/utils/context/UserContext.jsx";

export default function AdminPage() {
  const { role } = useContext(UserContext);
  const { loadedData } = loadData(
    "http://localhost:8082/api/specific-absence/all"
  );

  return (
    <>
      <div className="AdminPage Absencesdegroupe">
        {role && role == "ADMIN" ? (
          <div className="admin-links">
            <h1>Administration</h1>
            <Link to={`/absence/group/create`}>
              Créer une absence de groupe
            </Link>
          </div>
        ) : (
          <h1>Absences de groupe</h1>
        )}
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
                  <td>{format(new Date(absence.dtDebut), "dd/MM/yyyy")}</td>
                  <td>{format(new Date(absence.dt_fin), "dd/MM/yyyy")}</td>
                  <td>{absence.type}</td>
                  <td>{absence.motif}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
