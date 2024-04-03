import { useEffect } from "react";
import { Link } from "react-router-dom";

import loadData from "./../../model/utils/hooks.jsx";

export default function TraitementDemande() {
  const { loadedData } = loadData("http://localhost:8082/api/absence/all");

  const formatDate = (dateString) => {
    const options = { year: "numeric", month: "2-digit", day: "2-digit" };
    return new Date(dateString).toLocaleDateString(undefined, options);
  };

  useEffect(() => {}, [loadedData]);

  return (
    <>
      <h1>Traitement des demandes</h1>
      <div className="classForTable">
        <table>
          <thead>
            <tr>
              <th className="classNum">Numéro de demande</th>
              <th>Nom et prénom</th>
              <th>Motif</th>
              <th className="classDate">Date de début</th>
              <th className="classDate">Date de fin</th>
              <th className="classType">Type</th>
              <th className="classAction">Actions</th>
            </tr>
          </thead>
          <tbody>
            {loadedData &&
              loadedData
                .filter((absence) => absence.status === "EN_ATTENTE_VALIDATION")
                .map((absence) => (
                  <tr key={absence.id}>
                    <td>{absence.id}</td>
                    <td>{absence.fullName}</td>
                    <td>{absence.motif}</td>
                    <td>{formatDate(absence.dt_debut)}</td>
                    <td>{formatDate(absence.dt_fin)}</td>
                    <td>{absence.type}</td>
                    <td className="classActionButtons">
                      <button className="valider">
                        <Link to={`/manager/${absence.id}/validee`}>
                          Valider
                        </Link>
                      </button>
                      <button className="refuser">
                        <Link to={`/manager/${absence.id}/rejetee`}>
                          Refuser
                        </Link>
                      </button>
                    </td>
                  </tr>
                ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
