package com.devil.service;

import java.util.List;
import com.devil.domain.User;

public interface UserService {
  List<User> list (String keyword) throws Exception;
}
