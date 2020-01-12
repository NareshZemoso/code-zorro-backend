package com.zemoso.codezorro.sessionmanagement.helper;

import com.zemoso.codezorro.sessionmanagement.entities.Audit;
import com.zemoso.codezorro.sessionmanagement.entities.Candidate;
import com.zemoso.codezorro.sessionmanagement.entities.Session;
import lombok.Data;
import org.springframework.stereotype.Component;


import java.util.ArrayList;

@Component
@Data
public class Store {
  ArrayList<Candidate> candidates;
}
