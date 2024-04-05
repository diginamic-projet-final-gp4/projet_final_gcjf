import { Link } from "react-router-dom";

import "./Rapport.css";

export default function RappGlobPage() {
  return (
    <>
      <h1>Rapport global</h1>
      <div className="Rapportglobal">
        <div className="Collaborateurs">
          <h2>Collaborateurs</h2>
          <ul>
            <li>
              <Link to="/rapport/collab-actu">
                Vue par département et par jour
              </Link>
            </li>
          </ul>
        </div>

        <div className="Histogramme">
          <h2>Histogramme</h2>
          <ul>
            <li>
              <Link to="/rapport/histogramme">
                Voir l&apos;histogramme des absences
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </>
  );
}
