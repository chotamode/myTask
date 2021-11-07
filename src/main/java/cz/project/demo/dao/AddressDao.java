package cz.project.demo.dao;

import cz.project.demo.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao extends BaseDao<Address>{

    public AddressDao() {
        super(Address.class);
    }


}
