import { useContext } from "react";
import { UserContext } from "../../model/utils/context/UserContext";

import "./CreerAbs.css";

export default function CreateAbs() {
  // Récupèrera le nom de l'utilisateur connecté une fois l'authentification implémentée
  const collaborateur = "1";

  const { postData } = useContext(UserContext);

  console.log(postData);

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Affiche un le json des données du formulaire
    const formData = new FormData(event.target);
    const data = {};
    formData.forEach((value, key) => {
      data[key] = value;
    });
    data.status = "INITIALE";
    console.log(data);

    postData("http://localhost:8082/api/absence/create", data);

    // window.location.href = "/absence";
  };

  return (
    <div className="create-abs">
      <h1>Créer une absence</h1>

      <form className="create-abs-form" onSubmit={handleSubmit}>
        <label>
          <span>Votre identifiant utilisateur</span>
          <input type="text" name="user_id" value={collaborateur} readOnly />
        </label>
        <label>
          <span>Type d&apos;absence</span>
          <select name="type">
            <option value="PAID_LEAVE">Congé</option>
            <option value="UNPAID_LEAVE">Congé sans solde</option>
            <option value="RTT_EMPLOYEE">RTT</option>
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
