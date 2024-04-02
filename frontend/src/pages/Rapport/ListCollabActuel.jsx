import { useEffect } from "react";
import loadData from "../../model/utils/hooks.jsx"

export default function ListCollabActuel() {
  const { loadedData } = loadData("http://localhost:8082/api/service/all")
  useEffect(() => {
    console.log(loadedData)
  },[loadedData]) 

  return (
    <>
      <h1>Vue par département et par jour</h1>
      
    </>
  );
}
