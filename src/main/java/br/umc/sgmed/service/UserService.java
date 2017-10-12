package br.umc.sgmed.service;

import br.umc.sgmed.po.User;

public interface UserService {
	public User findUserByEmail(String email);

	public void saveUser(User user);
}