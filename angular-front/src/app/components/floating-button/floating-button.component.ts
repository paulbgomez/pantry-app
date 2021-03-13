import { Component, OnInit } from '@angular/core';
import {speedDialFabAnimations} from '../../speed-dial-fab.animations';
import {UsersService} from '../../services/users.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-floating-button',
  templateUrl: './floating-button.component.html',
  styleUrls: ['./floating-button.component.scss'],
  animations: speedDialFabAnimations

})
export class FloatingButtonComponent implements OnInit {

  fabButtons = [
    {
      icon: 'person_off'
    },
    {
      icon: 'view_headline'
    },
    {
      icon: 'room'
    },
    {
      icon: 'lightbulb_outline'
    },
    {
      icon: 'lock'
    }
  ];
  buttons = [];
  fabTogglerState = 'inactive';

  constructor(public usersService: UsersService, private router: Router) { }

  ngOnInit(): void {
  }

  showItems(): void {
    this.fabTogglerState = 'active';
    this.buttons = this.fabButtons;
  }
  hideItems(): void {
    this.fabTogglerState = 'inactive';
    this.buttons = [];
  }
  onToggleFab(): void {
    this.buttons.length ? this.hideItems() : this.showItems();
  }

  logout(): void{
    this.usersService.deleteCookies();
    this.router.navigate(['login']).then();
  }
}
