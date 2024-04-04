import { createContext } from "react";

export const UserContext = createContext();

// eslint-disable-next-line react/prop-types
export default function UserContextProvider({ children }) {
  async function postData(url = "", donnees = {}) {
    let options = {
      method: "POST",
      cache: "no-cache",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: JSON.stringify(donnees),
    };

    const response = await fetch(url, options);

    return response;
  }

  async function updateData(url = "", donnees = {}) {
    let options = {
      method: "PUT",
      cache: "no-cache",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: JSON.stringify(donnees),
    };

    const response = await fetch(url, options);

    return response;
  }

  const signIn = async (email, password) => {
    const response = await postData("http://localhost:8082/api/login", {
      email: email,
      password: password,
    });
    if (response.error) {
      throw new Error("Login or password incorrect");
    }

    localStorage.setItem("isLogged", true);
  };

  const logOut = async () => {
    const response = await postData("http://localhost:8082/logout");
    if (response.ok) {
      localStorage.removeItem("isLogged");
      return;
    }

    alert("There was a problem trying to log you out");
  };

  async function deleteData(url = "") {
    let options = {
      method: "DELETE",
      cache: "no-cache",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
    };

    const response = await fetch(url, options);

    return response;
  }

  return (
    <UserContext.Provider value={{ signIn, logOut, postData, deleteData, updateData }}>
      {children}
    </UserContext.Provider>
  );
}
