import loadData from "./../../model/utils/hooks.jsx";

import "./Profil.css";

export default function Profil() {
  const { loadedData } = loadData("http://localhost:8082/api/user");

  return (
    <>
      <h1>Bonjour {loadedData.firstName}</h1>

      <div className="profil">
        <div className="informations-personnelles">
          <h2>Informations personnelles</h2>
          <ul>
            <li>
              <span>Prénom & nom</span>
              <span>
                {loadedData.firstName} {loadedData.lastName}
              </span>
            </li>
            <li>
              <span>Email:</span>
              <span>{loadedData.email}</span>
            </li>
            <li>
              <span>Role:</span>
              {loadedData.role === "ROLE_ADMIN" ? (
                <span>Admin</span>
              ) : loadedData.role === "ROLE_MANAGER" ? (
                <span>Manager</span>
              ) : (
                <span>Employé</span>
              )}
            </li>
            <li>
              <span>Service: </span>
              <span> {loadedData.service?.lastName}</span>
            </li>
          </ul>
        </div>
        <div className="conges">
          <h2>Nombres de congés</h2>
          <ul>
            <li>
              <span>RTT:</span>
              <span>{loadedData.rttEmployee} jours</span>
            </li>
            <li>
              <span>Congés payés:</span>
              <span>{loadedData.paidLeave} jours</span>
            </li>
            <li>
              <span>Congés sans solde:</span>
              <span>{loadedData.unpaidLeave} jours</span>
            </li>
          </ul>
        </div>
      </div>
    </>
  );
}
