import { useEffect, useState } from "react";

export default function useFetchData(url = "") {
  const [loadedData, setData] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    async function loadData() {
      setLoading(true);
      const res = await fetch(url);
      try {
        if (res.ok) {
          const dataReceived = await res.json();
          if (dataReceived) {
            setData(dataReceived);
            setLoading(false);
          } else {
            console.log("pas de data");
          }
        }
      } catch (e) {
        console.error("Impossible de récupérer la resource");
      }
    }

    loadData();
  }, []);


  return { loadedData, loading };
}