package ca.mydemo.tcoffice.service;

import ca.mydemo.tcoffice.domain.Client;
import ca.mydemo.tcoffice.domain.Opportunity;

import java.util.Date;
import java.util.List;

public interface ClientService {
    List<Client> loadClients(Date from, Date to);

    Client getClient(int bizId);

    List<Opportunity> loadOpportunity(int bizId);
}
