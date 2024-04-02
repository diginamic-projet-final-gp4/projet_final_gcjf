import { useEffect } from "react";
import loadData from "../../model/utils/hooks.jsx"

export default function ListCollabActuel() {
  const { loadedData } = loadData("http://localhost:8082/api/service/all")
  //const { loadedData2 } = loadData("http://localhost:8082/api/absence/service?id=1&month=3&year=2024)

  
  useEffect(() => {
    console.log(loadedData)

  },[loadedData]) 
  return (
    <>
      <h1>Vue par département et par jour</h1>
      
    </>
  );
}
