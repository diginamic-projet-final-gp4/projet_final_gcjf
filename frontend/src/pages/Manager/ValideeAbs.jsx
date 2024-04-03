import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import loadData from "./../../model/utils/hooks.jsx";

export default function ValideeAbs() {
  const { id } = useParams();
  const navigate = useNavigate();

  // eslint-disable-next-line no-unused-vars
  const { loadedData } = loadData(
    "http://localhost:8082/api/absence/" + id + "/validate"
  );

  function handleReturn() {
    navigate("/manager/traitement");
  }

  useEffect(() => {
    handleReturn();
  }, []);

  return (
    <>
      <h1>Validation de la demande {id}</h1>
    </>
  );
}
