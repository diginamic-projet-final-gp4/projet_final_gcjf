import { Link } from "react-router-dom";

export default function RappGlobPage() {
  return (
    <>
      <h1>Rapport global</h1>
      <div>
        <h2>Collaborateurs</h2>
        <ul>
          <li>
            <Link to="/rapport/collab-actu">
              Voir la liste des collaborateurs actuels
            </Link>
          </li>
        </ul>

        <h2>Histogramme</h2>
        <ul>
          <li>
            <Link to="/rapport/histogramme">
              Voir l&apos;histogramme des absences
            </Link>
          </li>
        </ul>
      </div>
    </>
  );
}
