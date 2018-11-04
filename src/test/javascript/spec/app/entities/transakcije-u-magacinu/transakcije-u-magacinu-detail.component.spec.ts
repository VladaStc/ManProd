/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManProdTestModule } from '../../../test.module';
import { TransakcijeUMagacinuDetailComponent } from 'app/entities/transakcije-u-magacinu/transakcije-u-magacinu-detail.component';
import { TransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';

describe('Component Tests', () => {
    describe('TransakcijeUMagacinu Management Detail Component', () => {
        let comp: TransakcijeUMagacinuDetailComponent;
        let fixture: ComponentFixture<TransakcijeUMagacinuDetailComponent>;
        const route = ({ data: of({ transakcijeUMagacinu: new TransakcijeUMagacinu(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [TransakcijeUMagacinuDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TransakcijeUMagacinuDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransakcijeUMagacinuDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.transakcijeUMagacinu).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
