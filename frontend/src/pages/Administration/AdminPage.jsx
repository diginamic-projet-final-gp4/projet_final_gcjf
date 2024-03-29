import { useEffect } from "react";
import { Link } from "react-router-dom";

import loadData from "./../../model/utils/hooks.jsx";

export default function AdminPage() {
  const { loadedData } = loadData("http://localhost:8082/api/user");

  useEffect(() => {
    console.log(loadedData);
  }, [loadedData]);

  return (
    <>
      <h1>Administration</h1>
      <div>
        <div className="admin-links">
          <Link to={`admin/traitement-abs`}>Traitement des absences</Link>
          <Link to={`absence/group/create`}>Créer une absence de groupe</Link>
        </div>
        <h2>Collaborateurs</h2>
        <div className="classForTable">
          <table>
            <thead>
              <tr>
                <th>Nom et prénom</th>
                <th>Adresse email</th>
                <th>Service</th>
                <th>Nombre d&apos;absences</th>
                <th className="classAction">Actions</th>
              </tr>
            </thead>
            <tbody>
              {loadedData &&
                loadedData.map((user) => (
                  <tr key={user.id}>
                    <td>
                      {user.lastName} {user.firstName}
                    </td>
                    <td>{user.email}</td>
                    <td>{user.service.lastName}</td>
                    <td>{user.absences.length}</td>
                    <td className="classActionButtons">
                      <button className="modifier">
                        <Link to={`/user/update/${user.id}`}>Modifier</Link>
                      </button>
                      <button className="supprimer">
                        <Link to={`/user/delete/${user.id}`}>Supprimer</Link>
                      </button>
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}
