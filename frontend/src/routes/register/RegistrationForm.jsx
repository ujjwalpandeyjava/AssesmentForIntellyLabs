import { useState } from "react";
import style from "./RegistrationForm.module.css"
import apiEndPoints from "../../action/api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router";

function RegistrationForm() {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    confirmPassword: '',
    userType: 'AdminUser',
  });
  const nav = new useNavigate();

  const handleChange = (event) => {
    setFormData({ ...formData, [event.target.name]: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    apiEndPoints.AUTH().registerNewUser(formData)
      .then(resp => {
        if (resp.status === 201) {
          return resp.data;
        } else {
          toast.error(resp.data.message);
          return;
        }
      }).then(resp => {
        console.log(resp);
        setFormData({ name: '', email: '', password: '', confirmPassword: '', userType: '' });
          toast.success("Successfully registered!");
          nav("/login");
      }).catch(err => {
        console.log(err);
        if (err.response.status === 409) {
          toast.error(err.response.data.Message)
        }
      });
  };

  return (
    <div className={style.registration}>
      <form onSubmit={handleSubmit} className={style.form}>

        <h1>Registration Form</h1>

        <label htmlFor="email" className="required">Email</label>
        <input type="email" id="email" name="email" value={formData.email} onChange={handleChange} required />

        <label htmlFor="password" className="required">Password</label>
        <input type="password" id="password" name="password" value={formData.password} onChange={handleChange} />

        <label htmlFor="confirmPassword" className="required">Confirm Password:</label>
        <input type="confirmPassword" id="confirmPassword" name="confirmPassword" value={formData.confirmPassword} onChange={handleChange} />

        <label htmlFor="userType" className="required">User Type</label>
        <select id="userType" name="userType" value={formData.userType} onChange={handleChange}>
          <option value="" disabled>Select User Type</option>
          {/* <option value="Guest">Guest</option> */}
          <option value="AdminUser">Admin User</option>
          <option value="NormalUser">Normal User</option>
        </select>

        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default RegistrationForm;