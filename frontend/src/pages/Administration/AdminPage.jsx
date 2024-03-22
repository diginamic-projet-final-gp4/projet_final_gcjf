export default function AdminPage() {
  return (
    <>
      <h1>Administration</h1>
      <div>
        <h2>Absence</h2>
        <ul>
          <li>
            <a href="admin/traitement-abs">Traitement des absence</a>
          </li>
        </ul>

        <h2>Collaborateurs</h2>
        <ul>
          <li>
            <a href="/admin/ajout-collab">Ajout d&apos;un collaborateur </a>
          </li>
          <li>
            <a href="/admin/suppr-collab">
              Suppression d&apos;un collaborateur
            </a>
          </li>
          <li>
            <a href="/admin/modif-collab">
              Modification d&apos;un collaborateur
            </a>
          </li>
        </ul>
      </div>
    </>
  );
}
