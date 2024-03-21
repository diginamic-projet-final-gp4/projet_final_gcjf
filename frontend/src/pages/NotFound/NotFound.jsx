import "./NotFound.css";

export default function NotFound() {
  return (
    <div className="not-found">
      <h1>404</h1>
      <p>La page demandée n&apos;existe pas ou a été déplacée</p>
      <iframe
        src="https://gifer.com/embed/7VE"
        title="Not found"
        width="480"
        height="480"
        allowFullScreen
      ></iframe>
    </div>
  );
}
