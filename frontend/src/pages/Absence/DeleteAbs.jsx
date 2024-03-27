import { useEffect } from "react";

import loadData from "./../../model/utils/hooks.jsx";

import "./GestionAbs.css";

export default function DeleteAbs() {
  let id;
  if (window.location && window.location.pathname) {
    id = window.location.pathname.split("/").pop();
  }

  const { loadedData } = loadData("http://localhost:8082/api/absence/" + id);

  useEffect(() => {
    console.log("data", loadedData);
  }, [loadedData]);

  return (
    <>
      <h1>Confirmer la suppression</h1>
      <div className="absences">
        <table>
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
      </div>
    </>
  );
}
