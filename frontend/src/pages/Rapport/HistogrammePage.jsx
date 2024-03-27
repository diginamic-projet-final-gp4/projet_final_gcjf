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
import { useEffect, useState } from "react";
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

/**
 * Récupère les 6 derniers mois à partir de la date donnée
 *
 */
function getLabelsForDate(date, options = {}) {
  const {
    startOfWeek = 0, // 0: dimanche, 1: lundi, etc.
    showWeekNumber = false,
    showYear = false,
    locale = "default",
  } = options;

  // Récupère les 24 dernières semaines à partir de la date donnée (6 mois)
  const labels = Array.from({ length: 24 }, (_, i) => {
    const newDate = new Date(date.getTime() - i * 7 * 24 * 60 * 60 * 1000);
    const startDate = getWeekStartDate(newDate, startOfWeek);
    const endDate = getWeekEndDate(startDate, 6);

    // Mois de début et de fin
    const startMonth = startDate.toLocaleString(locale, { month: "short" });
    const endMonth = endDate.toLocaleString(locale, { month: "short" });

    // Formatage de la chaîne de sortie
    let label = "";
    if (showWeekNumber) {
      label += getWeekNumber(startDate, locale) + " ";
    }

    // Affichage de la fourchette de semaine si nécessaire
    if (startMonth === endMonth) {
      label += `${startDate.getDate()} - ${endDate.getDate()} ${startMonth}`;
    } else {
      label += `${startDate.getDate()} ${startMonth} - ${endDate.getDate()} ${endMonth}`;
    }

    if (showYear) {
      label += ` ${startDate.getFullYear()}`;
    }
    return label;
  }).reverse();

  return labels;

  // Fonctions utilitaires

  function getWeekStartDate(date, startOfWeek) {
    const day = date.getDay();
    const diff = day - startOfWeek;
    const newDate = new Date(date.getTime() - diff * 24 * 60 * 60 * 1000);
    return newDate;
  }

  function getWeekEndDate(startDate, days) {
    const endDate = new Date(startDate.getTime() + days * 24 * 60 * 60 * 1000);
    return endDate;
  }

  function getWeekNumber(date) {
    const d = new Date(
      Date.UTC(date.getFullYear(), date.getMonth(), date.getDate())
    );
    const d1 = new Date(Date.UTC(d.getFullYear(), 0, 1));
    const diff = d - d1;
    return Math.floor(diff / (7 * 24 * 60 * 60 * 1000)) + 1;
  }
}

import loadData from './../../model/utils/hooks.jsx' 

export default function HistogrammePage() {
  const [selectedDate, setSelectedDate] = useState(new Date());
  const labels = getLabelsForDate(selectedDate);

  const [data, setData] = useState()
  
  const { loadedData } = loadData("http://localhost:8082/api/absence/all")
  
  useEffect(() => {
    console.log("data", loadedData)

    const datasets = loadedData.map((absence) => {
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
        label: `${absence.fullName}`,
        // "length: xx" permet de sortir le nombre de semaine à afficher
        data: Array.from({ length: 24 }, () => Math.floor(Math.random() * 10)), // Simule le nombre d'absences pour chaque mois
        backgroundColor: randomColor,
        borderColor: randomColor,
      };
    })


  






    setData({
      labels,
      datasets,
    })
  }, [loadedData, selectedDate])


  

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
        { data && 
          <Bar data={data} options={options} />
        }
      </div>
    </>
  );
}
