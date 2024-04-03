import "./Histogramme.css";
import useFetchData from "../../model/utils/hooks";
import { Bar } from "react-chartjs-2";
import { useEffect, useState } from "react";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export default function Histogramme({
  selectedService,
  selectedMonth,
  selectedYear,
}) {
  const { loadedData } = useFetchData(
    `http://localhost:8082/api/absence/service?id=${selectedService}&month=${selectedMonth}&year=${selectedYear}`
  );
  const [data, setData] = useState();

  useEffect(() => {
    setData({
      labels: createLabel(selectedYear, selectedMonth),
      datasets: [
        {
          label: "Sales",
          data: [540, 325, 702, 620],
          backgroundColor: [
            "rgba(255, 159, 64, 0.2)",
            "rgba(75, 192, 192, 0.2)",
            "rgba(54, 162, 235, 0.2)",
            "rgba(153, 102, 255, 0.2)",
          ],
          borderColor: [
            "rgb(255, 159, 64)",
            "rgb(75, 192, 192)",
            "rgb(54, 162, 235)",
            "rgb(153, 102, 255)",
          ],
          borderWidth: 1,
        },
      ],
    });

    console.log("turbo tchoin", loadedData);
  }, []);

  function createLabel(year, month) {
    const daysInMonth = new Date(year, month, 0).getDate();
    let tempArr = [];
    let i = 0;
    while (i < daysInMonth) {
      i++;
      tempArr.push(new Date(year, month, i).toString().split("").slice(0, 10).join(""));
    }
    return tempArr;
  }

  const options = {
    plugins: {
      title: {
        display: true,
        text: "Bar Chart",
      },
    },
  };

  return <>
  {JSON.stringify(loadedData)}
    {data && <Bar data={data} options={options} />}
  </>;
}
