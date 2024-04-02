import { useContext } from "react";
import { UserContext } from "../../model/utils/context/UserContext";

import "./Absense.css";

export default function CreerAbsenseGroup() {
  // Récupèrera le nom de l'utilisateur connecté une fois l'authentification implémentée
  const orgaId = "1";

  const { postData } = useContext(UserContext);


  const handleSubmit = async (event) => {
    event.preventDefault();

    // Affiche un le json des données du formulaire
    const formData = new FormData(event.target);
    const data = {};
    formData.forEach((value, key) => {
      data[key] = value;
    });

    postData("http://localhost:8082/api/specific-absence/create", data);

    // window.location.href = "/absence";
  };

  return (
    <div className="create-abs">
      <h1>Créer une absence de groupe</h1>

      <form className="create-abs-form" onSubmit={handleSubmit}>
        <label>
          <span>Votre identifiant d&apos;organisation</span>
          <input type="text" name="organization_id" value={orgaId} readOnly />
        </label>
        <label>
          <span>Type d&apos;absence</span>
          <select name="type">
            <option value="FERIEE">Fériée</option>
            <option value="RTT_EMPLOYER">RTT Employeur</option>
          </select>
        </label>
        <label>
          <span>Date de début</span>
          <input type="date" name="dt_debut" />
        </label>
        <label>
          <span>Date de fin</span>
          <input type="date" name="dt_fin" />
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
