import "./CreerAbs.css";

export default function CreerAbsenseGroup() {
  // Récupèrera le nom de l'utilisateur connecté une fois l'authentification implémentée
  const collaborateur = "Testing Name";

  const collaborateursImpactes = [
    {
      nom: "Jean",
      prenom: "Pierre",
      service: "Service 1",
      poste: "Compta",
    },
    {
      nom: "Pierre",
      prenom: "Jean",
      service: "Service 2",
      poste: "Informatique",
    },
  ];

  const handleSubmit = (event) => {
    event.preventDefault();
    // TODO: Envoi des données du formulaire au backend

    // Redirection vers la page de gestion des absences
    window.location.href = "/absence";
  };

  return (
    <div className="create-abs">
      <h1>Créer une absence de groupe</h1>

      <form className="create-abs-form" onSubmit={handleSubmit}>
        <label>
          <span>Collaborateur</span>
          <input
            type="text"
            name="collaborateur"
            value={collaborateur}
            disabled
          />
        </label>
        <label>
          <span>Type d&apos;absence</span>
          <select name="typeAbsence">
            <option value="jourFerie">Jour férié</option>
            <option value="rttEmployeur">RTT employeur</option>
          </select>
        </label>
        <label>
          <span>Date de début</span>
          <input type="date" name="dateDebut" />
        </label>
        <label>
          <span>Date de fin</span>
          <input type="date" name="dateFin" />
        </label>
        <label>
          <span>Liste des collaborateurs impactés</span>
          <table>
            <thead>
              <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Service</th>
                <th>Poste</th>
              </tr>
            </thead>
            <tbody>
              {collaborateursImpactes.map((collaborateur, index) => (
                <tr key={index}>
                  <td>{collaborateur.nom}</td>
                  <td>{collaborateur.prenom}</td>
                  <td>{collaborateur.service}</td>
                  <td>{collaborateur.poste}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </label>
        <button type="submit">Créer</button>
      </form>
    </div>
  );
}
