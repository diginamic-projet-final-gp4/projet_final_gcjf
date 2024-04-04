import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import loadData from "./../../model/utils/hooks.jsx";

export default function RejeteeAbs() {
  const { id } = useParams();
  const navigate = useNavigate();

  // eslint-disable-next-line no-unused-vars
  const { loadedData } = loadData(
    "http://localhost:8082/api/manager/absence/" + id + "/rejete"
  );

  function handleReturn() {
    navigate("/manager/traitement");
  }

  useEffect(() => {
    handleReturn();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      <h1>Rejet de la demande {id}</h1>
    </>
  );
}
