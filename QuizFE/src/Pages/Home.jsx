import React from "react";
import BannerBackground from "../Assets/home-banner-background.png";
import LandingImage from "../Assets/landingimage.png";
import Navbar from "../Components/Navbar";
import { FiArrowRight } from "react-icons/fi";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div className="home-container">
      <Navbar />
      <div className="home-banner-container">
        <div className="home-bannerImage-container">
          <img src={BannerBackground} alt="" />
        </div>
        <div className="home-text-section">
          <h3 className="primary-heading">Online Examination System</h3>
          <Link to="/login" style={{textDecoration: 'none '}}>
            <button className="secondary-button">
              Đăng nhập ngay <FiArrowRight />{" "}
            </button>
          </Link>
        </div>
        <div className="home-image-section">
          <img src={LandingImage} alt="" />
        </div>
      </div>
    </div>
  );
};

export default Home;
