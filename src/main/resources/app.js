// app.js

async function safeFetch(url, opts = {}) { opts.credentials = 'include'; opts.headers = { 'Content-Type': 'application/json', ...(opts.headers || {}) };

async function doFetch() { const res = await fetch(url, opts); let body = null; try { body = await res.json(); } catch {} return { ok: res.ok, status: res.status, body }; }

let res = await doFetch();

if (res.status === 401) { const refreshed = await fetch('/api/auth/refresh', { method: 'POST', credentials: 'include' }); if (refreshed.ok) { res = await doFetch(); } }

return res; }

async function register() { const email = emailInput.value, password = passwordInput.value; const res = await safeFetch('/api/auth/register', { method: 'POST', body: JSON.stringify({ email, password }) }); authMsg.innerText = res.body?.message || res.status; }

async function login() { const email = emailInput.value, password = passwordInput.value; const res = await safeFetch('/api/auth/login', { method: 'POST', body: JSON.stringify({ email, password }) }); authMsg.innerText = res.body?.message || res.status; if (res.ok) await checkLogin(); }

async function logout() { const res = await safeFetch('/api/auth/logout', { method: 'POST' }); if (res.ok) { showAuth(false); authMsg.innerText = res.body?.message || 'Logged out'; } }

async function isLogged() { const res = await safeFetch('/api/users'); return res.ok; }

async function checkLogin() { let loggedIn = await isLogged(); if (loggedIn) { const res = await safeFetch('/api/users'); const first = res.body?.[0]; userEmail.innerText = first?.email || '(unknown)'; profile.innerText = JSON.stringify(res.body, null, 2); showAuth(true); } else { showAuth(false); } }

function showAuth(loggedIn) { document.getElementById('notLogged').style.display = loggedIn ? 'none' : 'block'; document.getElementById('logged').style.display = loggedIn ? 'block' : 'none'; document.getElementById('profileArea').style.display = loggedIn ? 'block' : 'none'; }

const emailInput = document.getElementById('email'); const passwordInput = document.getElementById('password'); const authMsg = document.getElementById('authMsg'); const userEmail = document.getElementById('userEmail'); const profile = document.getElementById('profile');

checkLogin();