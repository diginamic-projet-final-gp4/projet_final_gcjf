import "./Login.css";
import { useRef, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";

import { UserContext } from "../../model/utils/context/UserContext";

export default function Login() {
  const navigate = useNavigate();
  const formRef = useRef();
  const inputs = useRef([]);
  const [validation, setValidation] = useState("");
  const { signIn } = useContext(UserContext);

  function resetAll() {
    inputs.current = [];
  }

  const addInputs = (el) => {
    if (el && !inputs.current.includes(el)) {
      inputs.current.push(el);
    }
  };

  const handleForm = async (e) => {
    e.preventDefault();

    // if (
    //   (inputs.current[1].value.length || inputs.current[2].value.length) < 6
    // ) {
    //   //pseudo validation côté front
    //   setValidation("Le mot de passe doit contenir au moins 6 caractères");
    //   setTimeout(() => {
    //     setValidation("");
    //   }, 7000);
    //   return;
    // }

    await signIn(inputs.current[0].value, inputs.current[1].value)
      .then(() => {
        navigate("/profile");
        setValidation("");
      })
      .catch((e) => {
        setValidation("Error, email or password incorrect");
      });

    resetAll();
  };

  return (
    <>
      <h1>Login</h1>

      <form
        className="form card"
        ref={formRef}
        onSubmit={handleForm}
        id="login-form"
      >
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
          <input
            ref={addInputs}
            type="email"
            name="email"
            id="email"
            placeholder="Email"
          />
        </div>
        <div className="field">
          <label htmlFor="password">Password</label>
          <input
            ref={addInputs}
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
