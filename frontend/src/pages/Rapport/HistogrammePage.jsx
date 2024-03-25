import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Bar } from "react-chartjs-2";
import { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

import "./HistogrammePage.css";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

/**
 * Données des utilisateurs simulées à la place d'une requête API
 */
const userData = [
  { firstName: "Jean", lastName: "1" },
  { firstName: "Albert", lastName: "2" },
  { firstName: "Leo", lastName: "3" },
  { firstName: "Kevin", lastName: "4" },
  { firstName: "Marc", lastName: "5" },
  { firstName: "Paul", lastName: "6" },
  { firstName: "Pierre", lastName: "7" },
  { firstName: "Jacques", lastName: "8" },
  { firstName: "Benoit", lastName: "9" },
  { firstName: "Julien", lastName: "10" },
];

/**
 * Options de configuration du graphique
 */
const options = {
  responsive: true,
  plugins: {
    legend: {
      position: "top",
    },
  },
};

const datasets = userData.map((user) => {
  /**
   * Génère une couleur aléatoire pour chaque utilisateur
   */
  const randomColor = `rgba(${Math.floor(Math.random() * 256)}, ${Math.floor(
    Math.random() * 256
  )}, ${Math.floor(Math.random() * 256)}, 0.7)`;

  /**
   * Retourne un objet contenant les données de l'utilisateur
   */
  return {
    label: `${user.firstName} ${user.lastName}`,
    data: Array.from({ length: 6 }, () => Math.floor(Math.random() * 10)), // Simule le nombre d'absences pour chaque mois
    backgroundColor: randomColor,
    borderColor: randomColor,
  };
});

function getLabelsForDate(date) {
  return Array.from({ length: 6 }, (_, i) => {
    const newDate = new Date(date);
    newDate.setMonth(date.getMonth() - i);
    return newDate.toLocaleString("default", { month: "long" });
  }).reverse();
}

export default function HistogrammePage() {
  const [selectedDate, setSelectedDate] = useState(new Date());
  const labels = getLabelsForDate(selectedDate);

  const data = {
    labels,
    datasets,
  };

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };
  return (
    <>
      <h1>Histogramme</h1>
      <div className="histogramme">
        <DatePicker
          selected={selectedDate}
          onChange={handleDateChange}
          dateFormat="dd/MM/yyyy"
        />
        <Bar data={data} options={options} />
      </div>
    </>
  );
}
