import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';

@Component({
    selector: 'jhi-obradni-sistem-detail',
    templateUrl: './obradni-sistem-detail.component.html'
})
export class ObradniSistemDetailComponent implements OnInit {
    obradniSistem: IObradniSistem;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ obradniSistem }) => {
            this.obradniSistem = obradniSistem;
        });
    }

    previousState() {
        window.history.back();
    }
}
