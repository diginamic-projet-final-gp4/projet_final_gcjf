/* eslint-disable no-unused-vars */
import React, { useState, useEffect } from "react";

import "./AdminPage.css";

export default function AdminPage() {
  const [collaborateurs, setCollaborateurs] = useState([
    {
      nom: "Jean",
      prenom: "Pierre",
      email: "jean.pierre@collaborateur.gcjf",
      service: "Ressources Humaines",
      statut: "Présent",
    },
    {
      nom: "Durand",
      prenom: "Marie",
      email: "marie.durand@collaborateur.gcjf",
      service: "Marketing",
      statut: "RTT",
    },
    {
      nom: "Martin",
      prenom: "Luc",
      email: "luc.martin@collaborateur.gcjf",
      service: "Informatique",
      statut: "Congé",
    },
  ]);

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

  function handleEdit(collaborateur) {
    // TODO : redirect to edit page
    console.log("Edit", collaborateur);
  }

  function handleDelete(collaborateur) {
    // TODO : delete collaborateur
    console.log("Delete", collaborateur);
  }

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
      <h1>Administration</h1>
      <div>
        <div className="admin-links">
          <a href="admin/traitement-abs">Traitement des absence</a>
          <a href="absence/group/create">Créer une absence de groupe</a>
        </div>
        <h2>Nouvelle absence</h2>
        <div className="new-abs">
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

        <h2>Collaborateurs</h2>
        <div className="collab-list">
          <table>
            <thead>
              <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Email</th>
                <th>Service</th>
                <th>Statut</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {collaborateurs.map((collaborateur, index) => (
                <tr key={index}>
                  <td>{collaborateur.nom}</td>
                  <td>{collaborateur.prenom}</td>
                  <td>{collaborateur.email}</td>
                  <td>{collaborateur.service}</td>
                  <td>{collaborateur.statut}</td>
                  <td className="actions">
                    <button
                      onClick={() => handleEdit(collaborateur)}
                      className="edit"
                    >
                      {" "}
                      Modifier{" "}
                    </button>
                    <button
                      onClick={() => handleDelete(collaborateur)}
                      className="delete"
                    >
                      {" "}
                      Supprimer{" "}
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
