import { Entity, PrimaryGeneratedColumn, Column, OneToOne, JoinColumn } from 'typeorm';
import { Organization } from './Organization';

@Entity()
export class Member {
    @PrimaryGeneratedColumn()
    id!: number;

    @OneToOne(type => Organization)
    @JoinColumn()
    organization!: Organization;

    @Column({ type: 'varchar', length: 50 })
    name!: string;

    @Column({ type: 'varchar', length: 500 })
    password!: string;

    @Column({ type: 'varchar', length: 300, nullable: true })
    image!: string;
}
