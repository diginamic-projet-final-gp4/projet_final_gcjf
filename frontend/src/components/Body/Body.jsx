import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./Body.css";

import Login from "../../pages/Login/Login";
import NotFound from "../../pages/NotFound/NotFound";
import Profile from "../../pages/Profile/MonProfile";
import SeeAbs from "../../pages/Absence/GestionAbs";
import CreateAbs from "../../pages/Absence/CreerAbs";
import ModifAbs from "../../pages/Absence/ModifAbs";
import RappGlobPage from "../../pages/Rapport/RapportGlobPage";
import HistogrammePage from "../../pages/Rapport/HistogrammePage";
import ListCollabActuel from "../../pages/Rapport/ListCollabActuel";
import AdminPage from "../../pages/Administration/AdminPage";
import TraitementAbs from "../../pages/Administration/TraitementAbs";

function Nav() {
  return (
    <nav className="site-navigation">
      <div className="navbar-logo">
        <a href="/">
          <img src="./logo.jpg" alt="logo" />
        </a>
      </div>
      <div className="navbar-links">
        <ul>
          <li>
            <a href="/">Connexion</a>
          </li>
          <li>
            <a href="/profile">Profil</a>
          </li>
          <li>
            <a href="/absence">Absence</a>
          </li>
          <li>
            <a href="/rapport">Rapport</a>
          </li>
          <li>
            <a href="/admin">Administration</a>
          </li>
          {/* {user && (
					<li className="site-nav-admin">
						Administration
						<ul>
							<li><Link to="/admin/home">Stock</Link></li>
							<li><Link to="/admin/create-dish">Add&nbsp;a&nbsp;dish</Link></li>
						</ul>
					</li>
				)} */}
        </ul>
      </div>
    </nav>
  );
}

export default function Body() {
  return (
    <div className="main">
      <Router>
        <Nav />
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/absence" element={<SeeAbs />} />
          <Route path="/absence/create" element={<CreateAbs />} />
          <Route path="/absence/modif" element={<ModifAbs />} />
          <Route path="/rapport" element={<RappGlobPage />} />
          <Route path="/rapport/histogramme" element={<HistogrammePage />} />
          <Route path="/rapport/collab-actu" element={<ListCollabActuel />} />
          <Route path="/admin" element={<AdminPage />} />
          <Route path="/admin/traitement-abs" element={<TraitementAbs />} />

          {/* <Route path='/admin/home' element={<AdminPage />} />
						<Route path='/admin//dish/:id' element={<DishAuthPage />} />
						<Route path="/admin/create-dish" element={<CreateDishPage />} />
						<Route path="/admin/dish/:id/update" element={<UpdateDishPage />} /> */}

          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    </div>
  );
}
