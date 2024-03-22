export default function RappGlobPage() {
  return (
    <>
      <h1>Rapport global</h1>
      <div>
        <h2>Collaborateurs</h2>
        <ul>
          <li>
            <a href="/rapport/collab-actu">
              Voir la liste des collaborateurs actuels
            </a>
          </li>
        </ul>

        <h2>Histogramme</h2>
        <ul>
          <li>
            <a href="/rapport/histogramme">
              Voir l&apos;histogramme des absences
            </a>
          </li>
        </ul>
      </div>
    </>
  );
}
