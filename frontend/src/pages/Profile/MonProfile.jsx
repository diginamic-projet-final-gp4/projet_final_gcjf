import loadData from "./../../model/utils/hooks.jsx";

import "./Profil.css";

export default function Profil() {
  const { loadedData } = loadData("http://localhost:8082/api/user");

  console.log(loadedData);

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
              {loadedData.role === "ADMIN" ? (
                <span>Admin</span>
              ) : loadedData.role === "MANAGER" ? (
                <span>Manager</span>
              ) : (
                <span>Employé</span>
              )}
            </li>
            {loadedData.manager && (
              <li>
                <span>Manager:</span>
                <span>
                  {loadedData.manager?.firstName} {loadedData.manager?.lastName}{" "}
                  | {loadedData.manager?.email}
                </span>
              </li>
            )}

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
