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

const labels = Array.from({ length: 6 }, (_, i) => {
  const date = new Date();
  date.setMonth(date.getMonth() - i);
  return date.toLocaleString("default", { month: "long" });
}).reverse();

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

const data = {
  labels,
  datasets,
};

export default function HistogrammePage() {
  return (
    <>
      <h1>Histogramme</h1>
      <Bar data={data} options={options} />
    </>
  );
}
