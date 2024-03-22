import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import "./Body.css";

import Login from "../../pages/Login/Login";
import NotFound from "../../pages/NotFound/NotFound";

function Nav() {
  return (
    <nav className="site-navigation">
      <ul>
        <li>
          <Link to="/presentation">Presentation</Link>
        </li>
        <li>
          <Link to="/menu">Menu</Link>
        </li>
        <li>
          <Link to="/location">Location</Link>
        </li>
        <li>
          <Link to="/cart">Cart 🛒</Link>
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
    </nav>
  );
}

export default function Body() {
  return (
    <div className="main">
      <Router>
        <Nav />
        <Routes>
          <Route path="/" element={<Login />}>
            {/* <Route path='/admin/home' element={<AdminPage />} />
						<Route path='/admin//dish/:id' element={<DishAuthPage />} />
						<Route path="/admin/create-dish" element={<CreateDishPage />} />
						<Route path="/admin/dish/:id/update" element={<UpdateDishPage />} /> */}
          </Route>

          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    </div>
  );
}
