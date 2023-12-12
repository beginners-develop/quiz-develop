import React from "react";
import { Link } from "react-router-dom";
import Navbar from "../Components/Navbar";

import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';

const defaultTheme = createTheme();

export default function ForgetPassword() {
    const handleSubmit = (event) => {
      event.preventDefault();
      const data = new FormData(event.currentTarget);
      console.log({
        email: data.get('email'),
        password: data.get('password'),
      });
    };
  
    return (
      <ThemeProvider theme={defaultTheme}>
        <Navbar/>
        <Container component="main" maxWidth="xs" sx={{pb: 10}}>
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Typography component="h1" variant="h5">
              Lấy lại mặt khẩu
            </Typography>
            <div style={{textAlign: 'center', paddingTop: '1rem'}}>
              Nhập email của bạn vào đây và chúng tôi sẽ gửi mật khẩu mới đến đó
            </div>
            <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Địa chỉ Email"
                name="email"
                autoComplete="email"
                autoFocus
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Xác nhận
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link to="/login">
                    Đăng nhập
                  </Link>
                </Grid>
                <Grid item>
                  <Link to="/">
                    {"Quay lại trang chủ"}
                  </Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    );
  }