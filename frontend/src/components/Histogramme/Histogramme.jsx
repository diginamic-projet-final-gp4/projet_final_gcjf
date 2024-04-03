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

class Dataset {
  constructor(label, data){
    this.label = label
    this.data = data
    this.backgroundColor = "#ff00f0"
    this.borderColor = "#00ff0f"
    this.borderWidth = 1
    this.data = Math.random() * 10
  }

  toObject(){
    return {
      label : this.label,
      data : this.data,
      backgroundColor : this.backgroundColor,
      borderColor : this.borderColor,
      borderWidth : this.borderWidth,
    }
  }
}

export default function Histogramme({
  selectedService,
  selectedMonth,
  selectedYear,
}) {
  const { loadedData } = useFetchData(
    `http://localhost:8082/api/absence/service?id=${selectedService}&month=${selectedMonth}&year=${selectedYear}`
  );
  const [data, setData] = useState(loadedData);
  const [labels, setLabels] = useState(createLabel(selectedYear, selectedMonth));

  useEffect(() => {
    setLabels(createLabel(selectedYear, selectedMonth))
    if(loadedData){
      setData({
        labels,
        datasets: loadedData?.map((user) => {
          return new Dataset(user.firstName, user.absences).toObject()
        }),
        
      });
    }
    
    console.log("tchoin", data);
  }, [loadedData]);

  function createLabel(year, month) {
    const daysInMonth = new Date(year, month, 0).getDate();
    let tempArr = [];
    let i = 0;
    while (i < daysInMonth) {
      i++;
      tempArr.push(
        new Date(year, month, i).toString().split("").slice(0, 10).join("")
      );
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

  return (
    <>
      {JSON.stringify(loadedData)}
      {!!data?.datasets?.length > 0 && <Bar data={data} options={options} />}
    </>
  );
}
