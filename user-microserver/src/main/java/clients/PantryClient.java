package clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("pantry-service")
public interface PantryClient {


}
