import "./Login.css";

export default function Login() {
  return (
    <>
      <div>Login</div>
      <div>
        <label htmlFor="email">Email</label>
        <input type="email" name="email" />

        <label htmlFor="password">Password</label>
        <input type="password" name="password" />
      </div>
    </>
  );
}
