import { useContext } from "react";
import { UserContext } from "../../model/utils/context/UserContext";

import "./Login.css";

export default function Login() {
  const validation = "";

  const { postData } = useContext(UserContext);

  const handleForm = async (e) => {
    e.preventDefault();
    const form = e.target;
    const email = form.email.value;
    const password = form.password.value;

    try {
      console.log(email, password);
      const response = await postData("http://localhost:8082/api/user/login", {
        email: email,
        password: password,
      });
      console.log(response);

      // Enregistrement du token en local
      if (response && response.token) {
        localStorage.token = `auth=${response.token}`;
      }
    } catch (error) {
      console.error(error);
      // eslint-disable-next-line no-alert
      alert("Login or password incorrect");
    }
  };

  return (
    <>
      <h1>Login</h1>
      <form className="form card" onSubmit={handleForm} id="login-form">
        <div className="card_header">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            width="24"
            height="24"
          >
            <path fill="none" d="M0 0h24v24H0z"></path>
            <path
              fill="currentColor"
              d="M4 15h2v5h12V4H6v5H4V3a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v18a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1v-6zm6-4V8l5 4-5 4v-3H2v-2h8z"
            ></path>
          </svg>
          <h1 className="form_heading">Connexion</h1>
        </div>
        <div className="field">
          <label htmlFor="email">Email</label>
          <input type="email" name="email" id="email" placeholder="Email" />
        </div>
        <div className="field">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            name="password"
            id="password"
            placeholder="Password"
          />
        </div>
        {validation !== "" && <p>{validation}</p>}
        <div className="field">
          <button type="submit">S&apos;identifier</button>
        </div>
      </form>
    </>
  );
}
