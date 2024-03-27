import { useState } from "react";

import "./TraitementDemande.css";

export default function TraitementDemande() {
  // eslint-disable-next-line no-unused-vars
  const [absences, setAbsences] = useState([
    {
      nom: "Jean",
      prenom: "Pierre",
      type: "RTT",
      dateDebut: "2021-08-01",
      dateFin: "2021-08-05",
      nbJoursOuvres: 4,
    },
    {
      nom: "Durand",
      prenom: "Marie",
      type: "Congé",
      dateDebut: "2021-08-10",
      dateFin: "2021-08-15",
      nbJoursOuvres: 5,
    },
    {
      nom: "Martin",
      prenom: "Luc",
      type: "RTT",
      dateDebut: "2021-08-20",
      dateFin: "2021-08-25",
      nbJoursOuvres: 4,
    },
  ]);

  function handleAccept(absence) {
    // TODO : accept absence
    console.log("Accept", absence);
  }

  function handleRefuse(absence) {
    // TODO : refuse absence
    console.log("Refuse", absence);
  }
  return (
    <>
      <h1> Traitement des demandes </h1>
      <div className="traitement-demande">
        <table>
          <thead>
            <tr>
              <th>Nom</th>
              <th>Prénom</th>
              <th>Type</th>
              <th>Date de début</th>
              <th>Date de fin</th>
              <th>Nb de jours ouvrés</th>
              <th>Motif</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {absences.map((absence, index) => (
              <tr key={index}>
                <td>{absence.nom}</td>
                <td>{absence.prenom}</td>
                <td>{absence.type}</td>
                <td>{absence.dateDebut}</td>
                <td>{absence.dateFin}</td>
                <td>{absence.nbJoursOuvres}</td>
                <td>{absence.motif}</td>
                <td className="actions">
                  <button
                    onClick={() => handleAccept(absence)}
                    className="accept"
                  >
                    {" "}
                    Accepter{" "}
                  </button>
                  <button
                    onClick={() => handleRefuse(absence)}
                    className="refuse"
                  >
                    {" "}
                    Refuser{" "}
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
