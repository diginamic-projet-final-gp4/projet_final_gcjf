import "./CreerAbs.css";

export default function CreateAbs() {
  // Récupèrera le nom de l'utilisateur connecté une fois l'authentification implémentée
  const collaborateur = "Testing Name";

  const handleSubmit = (event) => {
    event.preventDefault();
    // TODO: Envoi des données du formulaire au backend

    // Redirection vers la page de gestion des absences
    window.location.href = "/absence";
  };

  return (
    <div id="create-abs">
      <h1>Créer une absence</h1>

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
            <option value="conge">Congé</option>
            <option value="congeSansSolde">Congé sans solde</option>
            <option value="rtt">RTT</option>
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
          <span>Motif</span>
          <input type="text" name="motif" />
        </label>
        <button type="submit">Créer</button>
      </form>
    </div>
  );
}
