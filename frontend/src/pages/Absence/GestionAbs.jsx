import "./GestionAbs.css";
import "react-calendar/dist/Calendar.css";

export default function SeeAbs() {
  const absences = [
    {
      id: 1,
      motif: "Maladie",
      dateDebut: "2021-07-01",
      dateFin: "2021-07-05",
      statut: "Initiale",
      type: "Congé",
    },
    {
      id: 2,
      motif: "Congé",
      dateDebut: "2021-07-10",
      dateFin: "2021-07-15",
      statut: "En attente de validation",
      type: "Congé",
    },
    {
      id: 3,
      motif: "Maladie",
      dateDebut: "2021-07-20",
      dateFin: "2021-07-25",
      statut: "Validée",
      type: "RTT",
    },
    {
      id: 4,
      motif: "Congé",
      dateDebut: "2021-07-30",
      dateFin: "2021-08-05",
      statut: "Refusée",
      type: "Congé sans solde",
    },
  ];

  return (
    <>
      <h1>Gérer les absences</h1>
      <div>
        <ul>
          <li>
            <a href="/absence/create">Créer une absence</a>
          </li>
        </ul>
        <div className="absences">
          <h2>Absenses</h2>
          <table>
            <thead>
              <tr>
                <th className="classNum">Numéro de demande</th>
                <th>Motif</th>
                <th className="classDate">Date de début</th>
                <th className="classDate">Date de fin</th>
                <th>Statut</th>
                <th className="classType">Type</th>
                <th className="classAction">Action</th>
              </tr>
            </thead>
            <tbody>
              {absences.map((absence) => (
                <tr key={absence.id}>
                  <td>{absence.id}</td>
                  <td>{absence.motif}</td>
                  <td>{absence.dateDebut}</td>
                  <td>{absence.dateFin}</td>
                  <td>{absence.statut}</td>
                  <td>{absence.type}</td>
                  <td className="action">
                    {absence.statut === "Initiale" && (
                      <button className="modifier">Modifier</button>
                    )}
                    {absence.statut !== "Validée" &&
                      absence.statut !== "Refusée" && (
                        <button className="supprimer">Supprimer</button>
                      )}
                    {absence.statut === "Validée" && (
                      <span>Demande validée</span>
                    )}
                    {absence.statut === "Refusée" && (
                      <span>Demande refusée</span>
                    )}
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
