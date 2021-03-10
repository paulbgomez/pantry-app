import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CookieService} from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class PantryService {

  constructor(private http: HttpClient, private cookies: CookieService) { }

  getPantries()
  /*
  @PostMapping("pantry/{id}")
    PantryDTO add(@RequestBody @Valid PantryDTO pantryDTO, @PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("pantry/all/{id}")
    List<PantryDTO> findAll(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("pantry/{id}")
    PantryDTO getPantryById(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("pantry/{id}")
    void delete(@PathVariable Long id, @RequestBody @Valid PantryDTO pantryDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PatchMapping("pantry/{pantryId}/{productId}/{quantity}")
    void updatePantry(@PathVariable Long pantryId, @PathVariable Long productId, @PathVariable Integer quantity, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("product/{id}")
    ProductDTO findProductById(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("product")
    List<ProductDTO> findAllProducts(@RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("product")
    ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("product/{id}")
    void deleteProduct(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);
   */
}
