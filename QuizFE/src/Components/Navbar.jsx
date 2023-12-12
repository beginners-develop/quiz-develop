/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useState } from "react";
import Logo from "../Assets/HCMUT_official_logo.svg";
import Box from "@mui/material/Box";
import HomeIcon from "@mui/icons-material/Home";
import InfoIcon from "@mui/icons-material/Info";
import CommentRoundedIcon from "@mui/icons-material/CommentRounded";
import PhoneRoundedIcon from "@mui/icons-material/PhoneRounded";
import ShoppingCartRoundedIcon from "@mui/icons-material/ShoppingCartRounded";
import { Route, Link, Routes, useLocation } from "react-router-dom";

const Navbar = () => {
  const location = useLocation();
  const [openMenu, setOpenMenu] = useState(false);
  const menuOptions = [
    {
      text: "Home",
      icon: <HomeIcon />,
    },
    {
      text: "About",
      icon: <InfoIcon />,
    },
    {
      text: "Testimonials",
      icon: <CommentRoundedIcon />,
    },
    {
      text: "Contact",
      icon: <PhoneRoundedIcon />,
    },
    {
      text: "Cart",
      icon: <ShoppingCartRoundedIcon />,
    },
  ];
  return (
    <nav>
      <Box sx={{ display: "inline-flex", alignItems: "center" }}>
        <img src={Logo} alt="" width="50" height="50" />
        <Box sx={{ ml: 2, fontSize: 20 }}>Beginner Team</Box>
      </Box>
      <Box sx={{ display: "inline-flex", alignItems: "center" }}>
        <Link to="/forget-password" style={{ textDecoration: "none " }}>
          <button className="primary-button">Quên mặt khẩu</button>
        </Link>
      </Box>
    </nav>
  );
};

export default Navbar;
