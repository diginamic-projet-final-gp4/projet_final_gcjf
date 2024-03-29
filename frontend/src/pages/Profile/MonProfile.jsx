import loadData from "./../../model/utils/hooks.jsx";

import "./Profil.css";

export default function Profil() {
  const collaborateur = "1";
  const { loadedData } = loadData(
    "http://localhost:8082/api/user/" + collaborateur
  );

  return (
    <>
      <h1>Bonjour {loadedData.firstName}</h1>

      <h2>Informations personnelles</h2>
      <div className="informations-personnelles">
        <span>Prénom: {loadedData.firstName}</span>
        <span>Nom: {loadedData.lastName}</span>
        <span>Email: {loadedData.email}</span>
        <span>Rôle: {loadedData.role}</span>
        <span>Service: {loadedData.service?.lastName}</span>
        {loadedData.manager && <span>Manager: {loadedData.manager}</span>}
        <span>
          Nombre de jours de congés restants:
          <table>
            <thead>
              <tr>
                <th>Rtt</th>
                <th>Congés payés</th>
                <th>Congés sans solde</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{loadedData.rttEmployee}</td>
                <td>{loadedData.paidLeave}</td>
                <td>{loadedData.unpaidLeave}</td>
              </tr>
            </tbody>
          </table>
        </span>
      </div>
    </>
  );
}
